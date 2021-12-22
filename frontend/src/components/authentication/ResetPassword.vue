<template>
  <div>
    <v-dialog persistent v-model="dialog" width="40em">
      <v-card >
        <v-card-title>Reset Your Password</v-card-title>
        <v-form ref="reset_form" v-model="valid" lazy-validation>
          <v-container >
            <v-text-field
            class="mx-3"
              color="orange accent-4"
              v-model="password"
              :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[
                notEmpty,
                containsNumber,
                containsChar,
                lengthMoreThanSix,
              ]"
              :type="showPassword ? 'text' : 'password'"
              label="Password"
              @click:append="showPassword = !showPassword"
            ></v-text-field>

            <v-text-field
            class="mx-3"
              color="orange accent-4"
              v-model="confirmPassword"
              :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[
                notEmpty,
                containsNumber,
                containsChar,
                lengthMoreThanSix,
                passwordMatch,
              ]"
              :type="showPassword ? 'text' : 'password'"
              label="Confirm Password"
              @click:append="showPassword = !showPassword"
            ></v-text-field>
          </v-container>
        </v-form>
        <v-alert v-if="this.hasError" type="error" outlined class="mx-4">
          {{ errorMessage }}
        </v-alert>
        <v-alert v-if="this.isSuccess" type="success" outlined class="mx-4">
          {{ successMessage }}
        </v-alert>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            v-if="!this.isSuccess"
            color="orange accent-4"
            text
            @click="submitResetRequest"
            :loading="this.loading"
          >
            Submit
          </v-btn>
          <v-btn
            v-else
            color="orange accent-4"
            text
            @click="redirectToMainPage"
          >
            Go to Main Page
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import { defineComponent } from "@vue/composition-api";
import AuthenticationRequest from "../../httpRequests/AuthenticationRequest";

export default defineComponent({
  setup() {},
  name: "ResetPassword",
  data() {
    return {
      token: "",
      dialog: true,
      valid: false,
      errMessage: "",
      hasError: false,
      password: "",
      showPassword: false,
      confirmPassword: "",
      isSuccess: false,
      successMessage: "",
      errorMessage: "",
      loading: false,
    };
  },
  async created() {
    this.token = this.$route.query.token;
  },
  methods: {
    validate() {
      return (
        this.$refs.reset_form.validate() &&
        this.password === this.confirmPassword
      );
    },
    async submitResetRequest() {
      this.hasError = false;
      if (this.validate()) {
        this.loading = true;
        await AuthenticationRequest.sendResetPasswordRequest(
          this.password,
          this.token
        )
          .then((response) => {
            this.successMessage = response.data.message;
            this.isSuccess = true;
          })
          .catch((err) => {
            this.hasError = true;
            this.errorMessage = err.response.data.message;
          });
        this.loading = false;
      }
    },
    redirectToMainPage() {
      this.$router.push("/hostels");
    },
    //password validations
    notEmpty(v) {
      return !!v || "Password is required.";
    },

    containsNumber(v) {
      return /[0-9]/.test(v) || "Password should contains at least one number";
    },

    containsChar(v) {
      return (
        /[A-Za-z]/.test(v) || "Password should contains at least one character"
      );
    },

    lengthMoreThanSix(v) {
      return (
        (v && v.length >= 6) ||
        "Password should be greater than or equal to 6 characters"
      );
    },
  },
  computed: {
    passwordMatch() {
      return () =>
        this.password === this.confirmPassword || "Password must match";
    },
  },
});
</script>
