<template>
  <v-card class="mx-auto">
    <v-navigation-drawer
      app
      class="orange accent-4"
      dark
      :mini-variant="mini"
      width="200"
      permanent
      id="side-nav"
    >
      <v-list>
        <v-list-item
          :to="item.route"
          v-for="item in items"
          :key="item.title"
          link=""
          :title="item.title"
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
        { title: "Food", icon: "mdi-food", route: "/stalls" },
        {
          title: "Study Areas",
          icon: "mdi-book-open-page-variant-outline",
          route: "/studyAreas",
        },
      ],
      mini: false,
    };
  },
  created() {
    window.addEventListener("resize", this.handleScreenResize);
  },
  destroyed() {
    window.removeEventListener("resize", this.handleScreenResize);
  },
  mounted() {
    this.handleScreenResize();
  },
  methods: {
    handleScreenResize() {
      var widthToHeightRatio = window.innerWidth / window.innerHeight;
      if (widthToHeightRatio < 4 / 3) {
        this.mini = true;
      } else {
        this.mini = false;
      }
    },
  },
};
</script>

<style scoped>
.title {
  padding: 10px;
}

#side-nav {
  padding-top: 4rem;
  /* max-width: max-content; */
  min-height: 100%;
}
</style>
