<template>
  <div>
    <v-card>
      <v-card-title>
        <span>Enter Your Registered Email</span>
      </v-card-title>
      <v-form ref="forget_form" v-model="valid" lazy-validation>
        <v-container>
          <v-text-field
            ref="email_field"
            color="orange accent-4"
            class="mx-3"
            label="Email*"
            :rules="emailRules"
            required
            v-model="email"
          ></v-text-field>
        </v-container>
      </v-form>
        <v-alert v-if="this.hasError" type="error" outlined class="mx-4"> {{ errorMessage }} </v-alert>
        <v-alert v-if="this.isSuccess" type="success" outlined class="mx-4"> {{ successMessage }} </v-alert>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn text @click="sendResetPasswordReq" :loading="this.loading"> Ok </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import { defineComponent } from "@vue/composition-api";
import AuthenticationRequest from "../../httpRequests/AuthenticationRequest";

export default defineComponent({
  setup() {},
  name: "ForgetPasswordForm",
  data() {
    return {
      valid: false,
      email: "",
      emailRules: [
        (v) => !!v || "E-mail is required",
        (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
      ],
      hasError: false,
      errorMessage: "An error has occurred, please try again",
      loading: false,
      successMessage: "Success",
      isSuccess: false
    };
  },
  methods: {
    validate() {
      return this.$refs.forget_form.validate();
    },
    async sendResetPasswordReq() {
        if (this.validate()) {
            this.loading = true;
            await AuthenticationRequest.sendResetPasswordRequest(this.email)
            .then((response) => {
                this.loading = false;
                this.successMessage = response.data.message;
                this.isSuccess = true;
            })
            .catch((err) => {
                this.hasError = true;
                this.errorMessage = err.response.data.message;
            })
        }
    }
  },
});
</script>
