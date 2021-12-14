<template>
    <div>

    <v-form
      ref="form"
      v-model="valid"
      lazy-validation
    >
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

export default ({
    name: "LoginForm",
    data: () => ({
    valid: true,
    username: '',
    nameRules: [
      v => !!v || 'UserName is required',
      v => (v && v.length <= 10) || 'Name must be less than 10 characters',
    ],
    password: '',
    showPassword: false,
     rules: {
        required: value => !!value || 'Password is required.',
      },
  }),
  methods: {
    validate() {
        return this.$refs.form.validate()
    },

    async submitLoginCredentials() {
         if (this.validate()) {
            await AuthenticationRequest.loginWithCredentials(
              this.username,
              this.password
          );
      }
    }

  },
})
</script>