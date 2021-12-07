<template>
  <div id="app">
    <h1>Rate NUS!</h1>
    <hr>
    <router-link to="/hostels"> Hostels </router-link>
    <router-link to="/canteens"> Canteens </router-link>
    <router-link to="/studyAreas"> Study Areas </router-link>
    <hr>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
