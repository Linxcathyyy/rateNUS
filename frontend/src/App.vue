<template>
  <v-app>
    <v-main>
      <div id="app">
        <header>
          <h1 id="ratenus">Rate NUS!</h1>
          <LoginButton id="login-button" />
        </header>
        <div id="nav-router-view">
          <Navigation id="navigation" />
          <div class="content mx-4">
            <router-view id="router-view" />
          </div>
        </div>
      </div>
    </v-main>
  </v-app>
</template>

<script>
import Navigation from "./components/Navigation.vue";
import LoginButton from "./components/authentication/LoginButton.vue";

export default {
  name: "App",
  components: {
    Navigation,
    LoginButton,
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
  display: grid;
  grid-template-rows: 1fr 8fr;
}
header {
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 4rem;
  margin: 0;
  background: #ffffff;
  box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
  z-index: 99; /* header stays on top */
  grid-column-start: 1;
  grid-column-end: 3;
}
#ratenus {
  padding: 15px;
}
#login-button {
  position: absolute;
  margin: 0% 0% 0% 85%;
}

#nav-router-view {
  display: grid;
  grid-template-columns: 3fr 17fr;
}

.content {
  display: flex;
  grid-column-start: 2;
}
#navigation {
  /* flex: 1; */
  margin-top: 4rem;
  height: 100%; /* Full-height: remove this if you want "auto" height */
  position: fixed; /* Fixed Sidebar (stay in place on scroll) */
  top: 0; /* Stay at the top */
  left: 0;
  bottom: 0;
  max-width: 15%;
}
#router-view {
  width: 100%;
  /* padding-left: 10rem;
  padding-right: 1rem;
  margin-bottom: 30px; */
  /* margin-left: 10%; */
}
</style>
