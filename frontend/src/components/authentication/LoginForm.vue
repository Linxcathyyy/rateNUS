<template>
    <div>
  
    <v-form
      ref="form"
      v-model="valid"
      lazy-validation
    >
      <v-text-field
        v-model="name"
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
        @click="onSubmit"
      >
        Log In
      </v-btn>
    </v-form>
</div>
</template>

<script>
import AuthenticationRequest from "../../httpRequests/AuthenticationRequest.js";

export default ({
    name: "LoginForm",
    data: () => ({
    valid: true,
    name: '',
    nameRules: [
      v => !!v || 'Username is required',
    ],
    password: '',
    showPassword: false,
     rules: {
        required: value => !!value || 'Password is required.',
      },
  }),

  methods: {
    async onSubmit() {
      if (this.$refs.form.validate()) {
        await AuthenticationRequest.processLoginForm();
      }
    },
  },
})
</script>
