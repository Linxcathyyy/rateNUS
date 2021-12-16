<template>
  <v-card class="mx-auto" height="100%">
    <v-navigation-drawer app class="blue accent-4" dark permanent id="side-nav">
      <v-list>
        <v-list-item
          :to="item.route"
          v-for="item in items"
          :key="item.title"
          link=""
        >
          <v-icon class="icon">{{ item.icon }}</v-icon>
          <v-list-item-title class="title">{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      items: [
        { title: "Hostels", icon: "mdi-bed", route: "/hostels" },
        { title: "Canteens", icon: "mdi-food", route: "/canteens" },
        {
          title: "Study Areas",
          icon: "mdi-book-open-page-variant-outline",
          route: "/studyAreas",
        },
      ],
    };
  },
};
</script>

<style scoped>
.title {
  padding: 10px;
}

#side-nav {
  padding-top: 4rem;
  max-width: max-content;
}
</style>
