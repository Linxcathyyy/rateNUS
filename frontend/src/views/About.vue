<template>
  <div>
    <h1 class="my-4 text-left">About RateNUS</h1>
    <v-divider></v-divider>
    <img 
      class="mt-6"
      :height="imageHeight"
      src="@/assets/about.svg" 
    />
    <p class="text-justify mt-6">
      RateNUS was created to improve the campus living experience of both incoming 
      and current students. It is an open platform that welcomes anyone's contribution 
      in providing accurate and useful information about campus living/studying.
    </p>
  </div>
</template>

<script>
export default {
  computed: {
      imageHeight() {
      switch (this.$vuetify.breakpoint.name) {
        case "xs":
          return 200;
        case "sm":
          return 250;
        case "md":
          return 300;
        case "lg":
          return 300;
        case "xl":
          return 300;
        default:
          return 300;
      }
    },
  }
};
</script>

<style></style>
