<template>
  <v-card class="mx-auto" height="100%" width="180">
    <v-navigation-drawer class="blue accent-4" dark permanent>
      <v-list>
        <v-list-item
          :to="item.route"
          v-for="item in items"
          :key="item.title"
          link=""
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
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

<style scoped></style>
