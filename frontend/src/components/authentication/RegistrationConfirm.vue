<template>
  <div>
    <h1>Registration Confirmation</h1>
    <p>{{ message }}</p>
  </div>
</template>

<script>
import { defineComponent } from "@vue/composition-api";
import AuthenticationRequest from "../../httpRequests/AuthenticationRequest";

export default defineComponent({
  setup() {},
  name: "RegistrationConfirm",
  data() {
    return {
      token: "",
      message: "",
    };
  },
  created() {
    this.token = this.$route.query.token;
    console.log(this.token);
  },
  async mounted() {
    await AuthenticationRequest.sendConfirmationRequest(this.token)
      .then((res) => {
        this.message = res.data.message;

        //redirect upon successful confirmation
        this.$router.push("/hostels");
      })
      .catch((err) => {
        this.message = err.response.data.message;
      });
  },
});
</script>
