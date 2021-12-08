<template>
  <nav>
    <ul>
      <router-link to="/hostels"> <li>Hostels</li> </router-link>
      <router-link to="/canteens"> <li>Canteens</li> </router-link>
      <router-link to="/studyAreas"> <li>Study Areas</li> </router-link>
    </ul>
  </nav>
</template>

<script></script>

<style scoped>
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin: 1rem;
  height: 5rem;
  width: 5rem;
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
