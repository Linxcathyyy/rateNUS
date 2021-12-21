<template>
  <div>
    <h1>Registration Confirmation</h1>
    <p>{{ message }}</p>
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
      message: "",
    };
  },
  created() {
    this.token = this.$route.query.token;
    console.log(this.token);
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
        this.$store.commit("logIn");
        this.$store.commit("updateDefaultProfileColor");

        this.$router.push("/hostels");
      })
      .catch((err) => {
        this.message = err.response.data.message;
      });
  },
});
</script>
