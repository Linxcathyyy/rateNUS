<template>
  <p class="rating">Rating: {{ score }}/5</p>
</template>

<script>
export default {
  props: ["score"],
};
</script>

<style scope>
.rating {
  color: rgb(32, 34, 58);
}
</style>
