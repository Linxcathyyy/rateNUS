<template>
  <div>
    <v-btn
      outlined
      depressed
      color="orange accent-4"
      @click="showLoginDialog"
      width="6.5em"
    >
      Login
    </v-btn>
    <v-dialog v-model="isLoginFormShown" max-width="40em">
      <v-card>
        <v-card-title>
          <span>Login</span>
        </v-card-title>
        <v-container class="px-6">
          <LoginForm @forget-password="showForgetPasswordDialog" />
        </v-container>
      </v-card>
    </v-dialog>
    <v-dialog v-model="isForgetPasswordShown" max-width="40em">
      <ForgetPasswordForm />
    </v-dialog>
  </div>
</template>

<script>
import { defineComponent } from "@vue/composition-api";
import LoginForm from "./LoginForm.vue";
import ForgetPasswordForm from "./ForgetPasswordForm.vue";

export default defineComponent({
  name: "LoginButton",
  setup() {},
  data() {
    return {
      isLoginFormShown: false,
      isForgetPasswordShown: false,
    };
  },
  methods: {
    showLoginDialog() {
      this.isLoginFormShown = true;
    },
    hideLoginDialog() {
      this.isLoginFormShown = false;
    },
    showForgetPasswordDialog() {
      this.hideLoginDialog();
      this.isForgetPasswordShown = true;
    },
  },
  components: {
    LoginForm,
    ForgetPasswordForm,
  },
  emits: ["forget-password"],
});
</script>
