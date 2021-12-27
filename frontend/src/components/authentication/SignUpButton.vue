<template>
  <div>
    <v-btn
      depressed
      class="orange accent-4"
      color="primary"
      @click="showSignUpDialog"
      width="6.5em"
    >
      Sign Up
    </v-btn>
    <v-dialog v-model="isSignUpDialogShown" max-width="40em">
      <v-card>
        <v-card-title>
          <span>Sign Up</span>
        </v-card-title>
        <v-container class="px-6">
          <SignUpForm />
        </v-container>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { defineComponent } from "@vue/composition-api";
import SignUpForm from "./SignUpForm.vue";
export default defineComponent({
  name: "LoginButton",
  setup() {},
  data() {
    return {
      isSignUpDialogShown: false,
    };
  },
  components: {
    SignUpForm,
  },
  methods: {
    showSignUpDialog() {
      this.isSignUpDialogShown = true;
    },
    quitSignUpDialog() {
      this.isSignUpDialogShown = false;
    },
  },
});
</script>
