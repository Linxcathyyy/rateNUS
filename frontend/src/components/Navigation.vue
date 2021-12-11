<template>
  <v-app>
  <nav>
    <li><router-link to="/hostels"> Hostels </router-link></li>
    <li><router-link to="/canteens"> Canteens</router-link></li>
    <li><router-link to="/studyAreas"> Study Areas </router-link></li>
  </nav>
  </v-app>
</template>

<script>
export default {
  name: "Navigation",
};
</script>

<style scoped>
nav {
  align-items: center;
  justify-content: center;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin: 1rem auto;
  padding: 0;
  height: 5rem;
  width: 80%;
  border-radius: 2rem;
  background: #f1f4fc;
  box-shadow: 0 0 20px #ccc;
  display: flex;
  align-items: center;
  justify-content: center;
}

a {
  text-decoration: none;
  color: rgb(0, 9, 61);
}
</style>
