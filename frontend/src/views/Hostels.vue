<template>
  <div>
    <h1>Hostel List</h1>
    <div v-for="hostel in hostelList" :key="hostel.id" class="hostel">
      <div class="hostel-info" @click="getIndividualHostel(hostel.id)">
        <h3>{{ hostel.id }}. {{ hostel.name }}</h3>
        <Location :location="hostel.location" />
        <Rating :score="hostel.rating" />
      </div>
      <Description :description="hostel.description" />
    </div>
  </div>
</template>

<script>
import HostelRequest from "../httpRequests/HostelRequest";
import Rating from "../components/hostels/Rating.vue";
import Location from "../components/hostels/Location.vue";
import Description from "../components/hostels/Description.vue";

export default {
  name: "Hostels",
  components: {
    Rating,
    Location,
    Description,
  },
  data() {
    return {
      hostelList: [],
    };
  },
  methods: {
    getHostelList() {
      HostelRequest.getHostelList()
        .then((response) => {
          console.log(response.data);
          this.hostelList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getIndividualHostel(id) {
      HostelRequest.getIndividualHostel(id)
        .then((response) => {
          console.log(response.data);
          this.hostelList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    }
  },
  mounted() {
    this.getHostelList();
  },
};
</script>

<style>
.hostel {
  list-style-type: none;
  background: rgba(201, 218, 241, 0.5);
  margin: 20px 50px;
  border-radius: 15px;
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
