<template>
  <v-app>
    <v-main>
      <div id="app">
        <header>
          <h1 id="ratenus">Rate NUS!</h1>
          <LoginButton
            v-if="!this.isLoggedIn"
            @logged-in="loggedIn"
            @id="login - button"
          />
          <UserProfile
            v-else
            :initials="this.initials"
            :fullName="this.fullName"
            :email="this.email"
            @logout="logout"
          />
        </header>
        <Navigation id="navigation" />
        <div class="content">
          <router-view id="router-view" />
        </div>
      </div>
    </v-main>
  </v-app>
</template>

<script>
import Navigation from "./components/Navigation.vue";
import LoginButton from "./components/authentication/LoginButton.vue";
import UserProfile from "./components/authentication/UserProfile.vue";
import UserUtil from "./components/authentication/UserUtil";

export default {
  name: "App",
  components: {
    Navigation,
    LoginButton,
    UserProfile,
  },
  data() {
    return {
      isLoggedIn: false,
      initials: "",
      fullName: "",
      email: "",
    };
  },
  methods: {
    loggedIn(response) {
      console.log("at app.vue" + response);
      this.isLoggedIn = true;
      this.initials = UserUtil.getInitials(response.data.username);
      this.fullName = response.data.username;
      this.email = response.data.email;
    },
    logout() {
      if (window.confirm("Are you sure you want to log out?")) {
        this.isLoggedIn = false;
      }
    },
  },
  emits: ["logged-in", "logout"],
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
  justify-content: center;
  align-items: center;
  height: 4rem;
  margin: 0;
  background: #f5f5f7;
  box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
  z-index: 99; /* header stays on top */
}
#ratenus {
  padding: 15px;
}
#login-button {
  position: absolute;
  margin: 0% 0% 0% 85%;
}
.content {
  display: flex;
}
#navigation {
  flex: 1;
  margin-top: 4rem;
  margin-left: 1rem;
  height: 100%; /* Full-height: remove this if you want "auto" height */
  width: 10%; /* Set the width of the sidebar */
  /* min-width: 100px; */
  position: fixed; /* Fixed Sidebar (stay in place on scroll) */
  top: 0; /* Stay at the top */
  left: 0;
  bottom: 0;
}
#router-view {
  width: 100%;
  /* padding-left: 10rem;
  padding-right: 1rem;
  margin-bottom: 30px; */
  margin-left: 10%;
}
</style>
