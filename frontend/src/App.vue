<template>
  <v-app>
    <v-main>
      <div id="app">
        <header>
          <h1 id="ratenus">Rate NUS!</h1>
          <div v-if="!this.$store.getters.isLoggedIn" class="login-component">
            <v-row>
              <LoginButton class="button" />
              <SignUpButton class="button" />
            </v-row>
          </div>
          <UserProfile
            v-else
            :initials="this.$store.getters.initials"
            :fullName="this.$store.getters.fullName"
            :email="this.$store.getters.email"
            :profileColor="this.$store.getters.defaultProfileColor"
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
import Navigation from "./components/util/Navigation.vue";
import LoginButton from "./components/authentication/LoginButton.vue";
import SignUpButton from "./components/authentication/SignUpButton.vue";
import UserProfile from "./components/authentication/UserProfile.vue";

export default {
  name: "App",
  components: {
    Navigation,
    LoginButton,
    SignUpButton,
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
  margin-top: 2rem;
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
.button {
  padding: 5px;
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
