<template>
  <v-menu bottom min-width="200px" rounded offset-y>
    <template v-slot:activator="{ on }">
      <v-btn icon x-large v-on="on">
        <v-avatar :color="profileColor" size="48">
          <span class="white--text text-h5">{{ initials }}</span>
        </v-avatar>
      </v-btn>
    </template>
    <v-card>
      <v-list-item-content class="justify-center">
        <div class="mx-auto text-center">
          <v-avatar :color="profileColor">
            <span class="white--text text-h5">{{ initials }}</span>
          </v-avatar>
          <v-container>
            <h3>{{ fullName }}</h3>
          </v-container>
          <h4 class="text-caption mt-1">
            {{ email }}
          </h4>
          <v-divider class="my-3"></v-divider>
          <v-btn depressed rounded text> Edit Account </v-btn>
          <v-divider class="my-3"></v-divider>
          <v-btn depressed rounded text @click="logout"> Log Out </v-btn>
        </div>
      </v-list-item-content>
    </v-card>
  </v-menu>
</template>

<script>
import { defineComponent } from "@vue/composition-api";

export default defineComponent({
  setup() {},
  name: "UserProfile",
  props: {
    initials: String,
    fullName: String,
    email: String,
    profileColor: String,
  },
  methods: {
    logout() {
      this.$store.commit("logOut");
    },
  },
});
</script>
