<template>
  <div>
    <v-form ref="signup_form" v-model="valid" lazy-validation>
      <v-alert dense outlined type="error" v-if="isErrorVisible">
        Login Failed
      </v-alert>
      <v-text-field
        autofocus
        v-model="username"
        :rules="nameRules"
        label="Username"
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
        :rules="passwordRules"
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
        Sign Up
      </v-btn>
    </v-form>
  </div>
</template>

<script>
import { defineComponent } from "@vue/composition-api";

export default defineComponent({
  setup() {},
  name: "SignUpForm",
  data: () => ({
    valid: true,
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
    passwordRules: [
      (v) => !!v || "Password is required.",
      (v) => /[0-9]/.test(v) || "Password should contains at least one number",
      (v) =>
        /[A-Za-z]/.test(v) || "Password should contains at least one character",
      (v) =>
        (v && v.length >= 6) ||
        "Password should be greater than or equal to 6 characters",
    ],
    isErrorVisible: false,
  }),
  methods: {
    validate() {
      return this.$refs.signup_form.validate();
    },
  },
});
</script>
