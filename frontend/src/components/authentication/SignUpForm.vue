<template>
  <div>
    <v-dialog v-model="successDialog" persistent max-width="290">
      <v-card>
        <v-card-title class="text-h5"> Success </v-card-title>
        <v-card-text
          >You have signed up successfully! Please proceed to log in with your
          registered account.</v-card-text
        >
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue" text @click="quitSuccessDialogAndReloadPage">
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog v-model="isErrorVisible" persistent max-width="290">
      <v-card>
        <v-card-title> Sign Up Failed </v-card-title>
        <v-card-text> {{ errorMessage }} </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue" text @click="quitFailureDialog"> Ok </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-form ref="signup_form" v-model="valid" lazy-validation>
      <v-text-field
        v-model="username"
        :rules="nameRules"
        label="User Name"
        required
      ></v-text-field>

      <v-text-field
        v-model="email"
        :rules="emailRules"
        label="Email"
        required
      ></v-text-field>

      <v-text-field
        v-model="password"
        :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
        :rules="[notEmpty, containsNumber, containsChar, lengthMoreThanSix]"
        :type="showPassword ? 'text' : 'password'"
        label="Password"
        @click:append="showPassword = !showPassword"
      ></v-text-field>

      <v-text-field
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

      <v-btn
        :disabled="!valid"
        color="success"
        class="mr-4"
        @click="this.submitSignUpCredentials"
      >
        Sign Up
      </v-btn>
    </v-form>
  </div>
</template>

<script>
import { defineComponent } from "@vue/composition-api";
import AuthenticationRequest from "../../httpRequests/AuthenticationRequest";

export default defineComponent({
  setup() {},
  name: "SignUpForm",
  data: () => ({
    valid: false,
    username: "",
    nameRules: [
      (v) => !!v || "Name is required",
      (v) => (v && v.length <= 20) || "Name must be less than 20 characters",
    ],
    email: "",
    emailRules: [
      (v) => !!v || "E-mail is required",
      (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
    ],
    password: "",
    showPassword: false,
    confirmPassword: "",
    isErrorVisible: false,
    successDialog: false,
    errorMessage: "",
  }),
  computed: {
    passwordMatch() {
      return () =>
        this.password === this.confirmPassword || "Password must match";
    },
  },
  methods: {
    validate() {
      return (
        this.$refs.signup_form.validate() &&
        this.password === this.confirmPassword
      );
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

    quitSuccessDialogAndReloadPage() {
      this.successDialog = false;
      location.reload();
    },

    quitFailureDialog() {
      this.isErrorVisible = false;
    },
    async submitSignUpCredentials() {
      if (this.validate()) {
        await AuthenticationRequest.signUpWithCredentials(
          this.username,
          this.password,
          this.email
        )
          .then((response) => {
            //handle success
            this.successDialog = true;
            return response;
          })
          .catch(function (err) {
            //handle error
            console.log("error occurred");
            return err.response;
            //return response;
          })
          .then((response) => {
            if (response.status != 200) {
              this.isErrorVisible = true;
              this.errorMessage = response.data.message;
            } else {
              this.successDialog = true;
              this.isErrorVisible = false;
            }
          });
      }
    },
  },
});
</script>
