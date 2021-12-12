<template>
    <v-btn
            color="primary"
            dark
            @click="this.directToLogin"
          >
           Log In
          </v-btn>
</template>

<script>
import { defineComponent } from '@vue/composition-api';


export default defineComponent({
    name: "LoginButton",
     methods: {
  directToLogin() {
    this.$router.push("/login");
  }
  },
        

})
</script>
