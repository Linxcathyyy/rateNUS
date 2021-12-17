<template>
  <v-menu :close-on-content-click="false">
    <template v-slot:activator="{ on, attrs }">
      <v-btn color="primary" dark v-bind="attrs" v-on="on"> Sign Up </v-btn>
    </template>
    <v-list>
      <v-list-item>
        <SignUpForm />
      </v-list-item>
    </v-list>
  </v-menu>
</template>

<script>
import { defineComponent } from "@vue/composition-api";
import SignUpForm from "./SignUpForm.vue";
export default defineComponent({
  name: "LoginButton",
  setup() {},
  methods: {},
  components: {
    SignUpForm,
  },
});
</script>