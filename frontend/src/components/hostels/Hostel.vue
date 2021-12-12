<template>
  <div :key="hostel.id" class="hostel">
    <div class="hostel-img">
      <img :src="hostel.imageUrl" alt="hostel image" />
    </div>
    <div class="hostel-info">
      <div class="name-and-location">
        <div class="name">{{ hostel.id }}. {{ hostel.name }}</div>
        <Location :location="hostel.location" />
      </div>
      <Rating :score="hostel.rating" class="rating" />

      <Description :description="hostel.description" class="description" />
    </div>
  </div>
</template>

<script>
import Rating from "./Rating.vue";
import Location from "./Location.vue";
import Description from "./Description.vue";

export default {
  name: "Hostel",
  components: {
    Rating,
    Location,
    Description,
  },
  props: {
    hostel: Object,
  },
};
</script>

<style scoped>
.hostel {
  list-style-type: none;
  max-height: 200px;
  background: rgba(201, 218, 241, 0.5);
  margin: 20px 0px;
  padding: 20px 0;
  border-radius: 30px;
  display: grid;
  grid-template-columns: 2fr 5fr;
  /* align-items: center; */
  justify-content: space-between;
  line-height: 100%;
}

.hostel-img {
  flex-shrink: 0;
  padding: 10px 40px;
}

.hostel-info {
  height: 200px;
  margin-right: 10px;
  border-left: 3px solid darkgray;
  display: grid;
  grid-template-columns: 4fr 1fr;
  grid-template-rows: 2fr 5fr;
}

img {
  height: 180px;
  width: 100%;
  object-fit: cover;
  justify-self: center;
}

.name {
  font-size: 20px;
  font-weight: bold;
}

.name-and-location {
  text-align: left;
  margin-left: 30px;
}

.rating {
  margin-right: 30px;
}

.description {
  grid-column-start: 1;
  grid-column-end: 3;
}
</style>
