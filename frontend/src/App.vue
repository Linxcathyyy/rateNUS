<template>
  <v-app>
    <v-main>
      <div id="app">
        <header>
          <div @click="goToMainPage">
            <h1 id="ratenus">RateNUS</h1>
          </div>
          <div v-if="!this.$store.getters.isLoggedIn" class="login-component">
            <v-row class="show-on-desktop">
              <LoginButton class="button" />
              <SignUpButton class="button" />
            </v-row>

            <div class="show-on-mobile">
              <v-app-bar-nav-icon
                @click.stop="menu = !menu"
              ></v-app-bar-nav-icon>
            </div>
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
        <div>
          <v-dialog v-model="menu" width="15em">
            <v-card>
              <v-card-title> Welcome! </v-card-title>
              <v-col align-content="space-around">
                <LoginButton class="button" />
                <SignUpButton class="button" />
              </v-col>
            </v-card>
          </v-dialog>
        </div>
        <div id="nav-router-view">
          <Navigation id="navigation" />
          <div class="mx-4">
            <router-view id="router-view" />
          </div>
          <Footer> </Footer>
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
import Footer from "./components/util/Footer.vue";

export default {
  name: "App",
  components: {
    Navigation,
    LoginButton,
    SignUpButton,
    UserProfile,
    Footer,
  },
  data() {
    return {
      menu: false,
    };
  },
  methods: {
    goToMainPage() {
      const currentPath = this.$router.history.current.path;
      if (currentPath !== "/hostels") {
        this.$router.push("/hostels");
      }
    },
  },
  watch: {
    $route(to) {
      document.title = `${to.meta.title} - RateNUS`;
    },
  },
};
</script>

<style>
@media (min-width: 761px) {
  .show-on-mobile {
    display: none !important;
  }
}
@media (max-width: 760px) {
  .show-on-desktop {
    display: none !important;
  }
}

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

@import url("https://fonts.googleapis.com/css2?family=Exo+2:wght@700&display=swap");
#ratenus {
  padding: 15px;
  color: #ff6d00;
  font-family: "Exo 2";
}
#ratenus:hover {
  cursor: pointer;
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
  margin-bottom: 15rem;
}
</style>
