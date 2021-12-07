<template>
  <div>
    <h1>Hostel List</h1>
    <div v-for="hostel in hostelList" :key="hostel.id">
      <div @click="goToViewMorePage(hostel.id)" id="hostel-click">
        <Hostel :hostel="hostel" />
      </div>
    </div>
  </div>
</template>

<script>
import HostelRequest from "../httpRequests/HostelRequest";
import Hostel from "../components/hostels/Hostel.vue";

export default {
  name: "Hostels",
  components: {
    Hostel,
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
    goToViewMorePage(hostelId) {
      this.$router.push("/hostels/" + hostelId);
    },
  },
  mounted() {
    this.getHostelList();
  },
};
</script>

<style scope>
#hostel-click:hover {
  cursor: pointer;
}
</style>
