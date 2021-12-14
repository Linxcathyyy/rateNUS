<template>
  <v-menu :close-on-content-click="false">
    <template v-slot:activator="{ on, attrs }">
      <v-btn color="primary" dark v-bind="attrs" v-on="on"> Log In </v-btn>
    </template>
    <v-list>
      <v-list-item>
        <LoginForm @logged-in="loggedIn" />
      </v-list-item>
    </v-list>
  </v-menu>
</template>

<script>
import { defineComponent } from "@vue/composition-api";
import LoginForm from "./LoginForm.vue";
export default defineComponent({
  name: "LoginButton",
  setup() {},
  methods: {
    loggedIn(response) {
      this.$emit("logged-in", response);
    },
  },
  components: {
    LoginForm,
  },
  emits: ["logged-in"],
});
</script>