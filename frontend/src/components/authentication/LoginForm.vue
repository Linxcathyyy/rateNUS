<template>
  <div>
    <v-form ref="login_form" v-model="valid" lazy-validation>
      <v-alert dense outlined type="error" v-if="isErrorVisible">
        {{ errorMessage }}
      </v-alert>
      <v-text-field
        color="orange accent-4"
        v-model="username"
        :rules="nameRules"
        label="Username"
        required
        v-on:keydown.enter="submitLoginCredentials"
      ></v-text-field>
      <v-text-field
        color="orange accent-4"
        v-model="password"
        :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
        :rules="[rules.required]"
        :type="showPassword ? 'text' : 'password'"
        name="input-10-1"
        label="Password"
        @click:append="showPassword = !showPassword"
        v-on:keydown.enter="submitLoginCredentials"
      ></v-text-field>

      <v-btn
        depressed
        outlined
        :disabled="!valid"
        color="orange accent-4"
        class="mr-4"
        @click="this.submitLoginCredentials"
      >
        Log In
      </v-btn>
      
    </v-form>
    <v-spacer></v-spacer>
    <v-btn class="mt-4" small text @click="forgetPassword"> Forget Password? </v-btn>
  </div>
</template>

<script>
import AuthenticationRequest from "../../httpRequests/AuthenticationRequest";
import AuthenticationUtil from "./AuthenticationUtil";

export default {
  name: "LoginForm",
  data: () => ({
    valid: false,
    username: "",
    nameRules: [(v) => !!v || "UserName is required"],
    password: "",
    showPassword: false,
    rules: {
      required: (value) => !!value || "Password is required.",
    },
    isErrorVisible: false,
    errorMessage: "",
  }),
  methods: {
    validate() {
      return this.$refs.login_form.validate();
    },

    async submitLoginCredentials() {
      if (this.validate()) {
        await AuthenticationRequest.loginWithCredentials(
          this.username,
          this.password
        )
          .then((response) => {
            console.log(response.data);
            var id = response.data.id;
            var name = response.data.username;
            var email = response.data.email;
            var token = AuthenticationUtil.parseJWTToken(
              response.headers["authorization"]
            );
            var roles = response.data.roles;
            var role = "USER";
            for (let i in roles) {
              if (roles[i] === "ADMIN") {
                  role = "ADMIN";
              }
            }
            this.$store.commit("changeId", id);
            this.$store.commit("changeName", name);
            this.$store.commit("changeEmail", email);
            this.$store.commit("updateJwtToken", token);
            this.$store.commit("changeRole", role);
            this.$store.commit("logIn");
            this.$store.commit("updateDefaultProfileColor");

            return response;
          })
          .catch(function (err) {
            //handle error
            console.log("error occurred: " + err);
            return err.response;
          })
          .then((response) => {
            if (response.status != 200) {
              this.isErrorVisible = true;
              this.errorMessage = response.data.message;
            } else {
              this.isErrorVisible = false;
              location.reload();
            }
          });
      }
    },
    forgetPassword() {
      this.$emit('forget-password');
    }
  },
};
</script>
