<template>
  <div>
    <v-dialog v-model="hasError" width="40em">
      <v-card>
        <v-card-title>Error</v-card-title>
        <v-card-content>
          {{ errMessage }}
        </v-card-content>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="orange accent-4" text @click="redirectToMainPage">
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { defineComponent } from "@vue/composition-api";
import AuthenticationRequest from "../../httpRequests/AuthenticationRequest";
import AuthenticationUtil from "../authentication/AuthenticationUtil";

export default defineComponent({
  setup() {},
  name: "RegistrationConfirm",
  data() {
    return {
      token: "",
      errMessage: "",
      hasError: false,
    };
  },
  created() {
    this.token = this.$route.query.token;
  },
  async mounted() {
    await AuthenticationRequest.sendConfirmationRequest(this.token)
      .then((response) => {
        var id = response.data.id;
        var name = response.data.username;
        var email = response.data.email;
        var token = AuthenticationUtil.parseJWTToken(
          response.headers["authorization"]
        );
        this.$store.commit("changeId", id);
        this.$store.commit("changeName", name);
        this.$store.commit("changeEmail", email);
        this.$store.commit("updateJwtToken", token);
        this.$store.commit("changeRole", "USER");
        this.$store.commit("logIn");
        this.$store.commit("updateDefaultProfileColor");

        this.$router.push("/hostels");
      })
      .catch((err) => {
        this.errMessage = err.response.data.message;
        this.hasError = true;
      });
  },
  methods: {
    redirectToMainPage() {
      this.$router.push("/hostels");
    },
  },
});
</script>
