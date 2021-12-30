<template>
  <v-dialog v-model="this.isSessionExpired" persistent max-width="290">
    <v-card>
      <v-card-title style="word-break: break-word">
        Your Session Has Expired
      </v-card-title>
      <v-card-text>Please log in again</v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="orange accent-4" text @click="logOut"> Ok </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { defineComponent } from "@vue/composition-api";

export default defineComponent({
  setup() {},
  name: "SessionExpireDialog",
  props: {
    isSessionExpired: Boolean,
  },
  methods: {
    logOut() {
      this.$store.commit("logOut");
      location.reload();
    },
  },
});
</script>
