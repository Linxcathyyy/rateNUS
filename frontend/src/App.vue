<template>
  <v-app>
    <v-main>
      <div id="app">
        <header>
          <h1 id="ratenus">Rate NUS!</h1>
          <LoginButton
            v-if="!this.$store.getters.isLoggedIn"
            id="login-button"
            class="login-component"
          />
          <UserProfile
            v-else
            :initials="this.$store.getters.initials"
            :fullName="this.$store.getters.fullName"
            :email="this.$store.getters.email"
            class="login-component"
          />
        </header>
        <div id="nav-router-view">
          <Navigation id="navigation" />
          <div class="mx-4">
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
import UserProfile from "./components/authentication/UserProfile.vue";

export default {
  name: "App",
  components: {
    Navigation,
    LoginButton,
    UserProfile,
  },
  data() {
    return {};
  },
  methods: {},
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

header {
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 4rem;
  margin: 0;
  background: #ffffff;
  box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
  z-index: 99; /* header stays on top */
}
#ratenus {
  padding: 15px;
}
.login-component {
  padding: 15px;
}
#navigation {
  margin-top: 4rem;
  height: 100%; /* Full-height: remove this if you want "auto" height */
  top: 0; /* Stay at the top */
  left: 0;
  bottom: 0;
}
#router-view {
  width: 100%;
}
</style>
