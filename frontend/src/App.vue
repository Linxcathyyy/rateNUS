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
      <LoginButton />
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
import LoginButton from "./components/authentication/LoginButton.vue"

export default {
  name: 'App',
  components: {
    Navigation,
    LoginButton
  },
  data: () => ({
  }),
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
  position: fixed;
  left: 0;
  top: 7rem;
  bottom: 0;
}
#router-view {
  width: 100%;
  padding-left: 7rem;
  padding-right: 1rem;
  margin-bottom: 30px;
}
</style>
