<template>
  <div>
    <v-btn class="orange accent-4" color="primary" @click="showLoginDialog">
      Login
    </v-btn>
    <v-dialog v-model="isLoginFormShown" max-width="40em">
      <v-card>
        <v-card-title>
          <span>Login</span>
        </v-card-title>
        <v-container>
          <LoginForm />
        </v-container>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { defineComponent } from "@vue/composition-api";
import LoginForm from "./LoginForm.vue";
export default defineComponent({
  name: "LoginButton",
  setup() {},
  data() {
    return {
      isLoginFormShown: false,
    };
  },
  methods: {
    showLoginDialog() {
      this.isLoginFormShown = true;
    },
  },
  components: {
    LoginForm,
  },
});
</script>
