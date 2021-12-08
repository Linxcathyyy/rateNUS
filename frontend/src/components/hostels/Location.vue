<template>
  <p class="location">Location: {{ location }}</p>
</template>

<script>
export default {
  props: ["location"],
};
</script>

<style scope>
.location {
  color: rgb(8, 24, 197);
}
</style>
