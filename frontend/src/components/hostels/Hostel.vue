<template>
  <div>
    <div :key="hostel.id" class="hostel">
      <div class="hostel-info">
        <h3>{{ hostel.id }}. {{ hostel.name }}</h3>
        <Location :location="hostel.location" />
        <Rating :score="hostel.rating" />
      </div>
      <Description :description="hostel.description" />
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

<style>
.hostel {
  list-style-type: none;
  background: rgba(201, 218, 241, 0.5);
  margin: 20px 0px;
  border-radius: 30px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.hostel-info {
  flex-shrink: 0;
  width: 20%;
  padding: 10px 40px;
}
</style>
