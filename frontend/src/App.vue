<template>
  <v-app>
    <v-main>
      <div id="app">
        <h1 id="ratenus">Rate NUS!</h1>
        <!-- <hr />
      <router-link to="/hostels"> Hostels </router-link>
      <router-link to="/canteens"> Canteens </router-link>
      <router-link to="/studyAreas"> Study Areas </router-link>
      <hr /> -->

        <div class="content">
          <Navigation id="navigation" />
          <router-view id="router-view" />
        </div>
      </div>
    </v-main>
  </v-app>
</template>

<script>
import Navigation from "./components/Navigation.vue";

export default {
  name: "App",
  components: {
    Navigation,
  },
  data: () => ({}),
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 4rem;
}
#ratenus {
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 4rem;
  margin: 0;
  background: #f5f5f7;
  box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
}
.content {
  display: flex;
}
#navigation {
  flex: 1;
  margin-top: 6rem;
  margin-left: 1rem;
  height: 100%; /* Full-height: remove this if you want "auto" height */
  width: 10%; /* Set the width of the sidebar */
  min-width: 100px;
  position: fixed; /* Fixed Sidebar (stay in place on scroll) */
  top: 0; /* Stay at the top */
  left: 0;
  overflow-x: hidden; /* Disable horizontal scroll */
  padding-top: 20px;
  /* background: pink; */
}
#router-view {
  margin-left: 150px;
  flex: 7 auto;
}
</style>
