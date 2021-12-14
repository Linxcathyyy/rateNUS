<template>
  <div>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-alert dense outlined type="error" v-if="isErrorVisible">
        Login Failed
      </v-alert>
      <v-text-field
        v-model="username"
        :counter="10"
        :rules="nameRules"
        label="Username"
        required
      ></v-text-field>
      <v-text-field
        v-model="password"
        :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
        :rules="[rules.required]"
        :type="showPassword ? 'text' : 'password'"
        name="input-10-1"
        label="Password"
        @click:append="showPassword = !showPassword"
      ></v-text-field>
      <v-btn
        :disabled="!valid"
        color="success"
        class="mr-4"
        @click="this.submitLoginCredentials"
      >
        Log In
      </v-btn>
    </v-form>
  </div>
</template>

<script>
import AuthenticationRequest from "../../httpRequests/AuthenticationRequest";

export default {
  name: "LoginForm",
  data: () => ({
    valid: true,
    username: "",
    nameRules: [
      (v) => !!v || "UserName is required",
      (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
    ],
    password: "",
    showPassword: true,
    rules: {
      required: (value) => !!value || "Password is required.",
    },
    isErrorVisible: false,
  }),
  methods: {
    validate() {
      return this.$refs.form.validate();
    },

    showSubmissionError() {
      this.isErrorVisible = true;
    },

    async submitLoginCredentials() {
      if (this.validate()) {
        await AuthenticationRequest.loginWithCredentials(
          this.username,
          this.password
        )
          .then((response) => {
            console.log("in login form:" + response);
            this.$emit("logged-in", response);
          })
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
};
</script>