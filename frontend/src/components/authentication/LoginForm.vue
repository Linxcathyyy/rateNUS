<template>
    <div id="app">
  <v-app id="inspire">
    <v-form
      ref="form"
      v-model="valid"
      lazy-validation
    >
      <v-text-field
        v-model="name"
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
  
      <!-- <v-text-field
        v-model="email"
        :rules="emailRules"
        label="E-mail"
        required
      ></v-text-field> -->
  
      <v-btn
        :disabled="!valid"
        color="success"
        class="mr-4"
        @click="validate"
      >
        Log In
      </v-btn>
    </v-form>
  </v-app>
</div>
</template>

<script>
export default ({
    name: "LoginForm",
    data: () => ({
    valid: true,
    name: '',
    nameRules: [
      v => !!v || 'UserName is required',
      v => (v && v.length <= 10) || 'Name must be less than 10 characters',
    ],
    // email: '',
    // emailRules: [
    //   v => !!v || 'E-mail is required',
    //   v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
    // ],
    password: '',
    showPassword: false,
     rules: {
        required: value => !!value || 'Password is required.',
      },
  }),

  methods: {
    validate () {
      this.$refs.form.validate()
    },
    reset () {
      this.$refs.form.reset()
    },
    resetValidation () {
      this.$refs.form.resetValidation()
    },
  },
})
</script>
