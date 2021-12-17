<template>
  <div>
    <v-form ref="signup_form" v-model="valid" lazy-validation>
      <v-alert dense outlined type="error" v-if="isErrorVisible">
        Sign Up Failed
      </v-alert>
      <v-text-field
        autofocus
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

      <v-btn :disabled="!valid" color="success" class="mr-4"> Sign Up </v-btn>
    </v-form>
  </div>
</template>

<script>
import { defineComponent } from "@vue/composition-api";

export default defineComponent({
  setup() {},
  name: "SignUpForm",
  data: () => ({
    valid: false,
    username: "",
    nameRules: [
      (v) => !!v || "Name is required",
      (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
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
  }),
  computed: {
    passwordMatch() {
      return () =>
        this.password === this.confirmPassword || "Password must match";
    },
  },
  methods: {
    validate() {
      return this.$refs.signup_form.validate();
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

    showSubmissionError() {
      this.isErrorVisible = true;
    },

    async submitSignUpCredentials() {
      if (this.validate()) {
        await AuthenticationRequest.signUpWithCredentials(
          this.username,
          this.password,
          this.email
        )
          // .then((response) => {
          //   handle success
          //   var name = response.data.username;
          //   var email = response.data.email;
          //   var token = AuthenticationUtil.parseJWTToken(
          //     response.headers["authorization"]
          //   );
          //   console.log(name, email, token);
          //   this.$store.commit("changeName", name);
          //   this.$store.commit("changeEmail", email);
          //   this.$store.commit("updateJwtToken", token);
          //   this.$store.commit("logIn");
          //   this.$store.commit("updateDefaultProfileColor");
          // })
          .catch(function (response) {
            //handle error
            console.log("error occurred");
            console.log(response);
            //return response;
          })
          .then(() => {
            this.showSubmissionError();
          });
      }
    },
  },
});
</script>
