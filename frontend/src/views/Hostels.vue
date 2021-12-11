<template>
  <div class="hostels">
    <h1>Hostel List</h1>
    <SearchBar @handle-search="handleSearch" searchHint="Search for hostels" />
    <div v-for="hostel in hostelList" :key="hostel.id" class="hostel-list">
      <div @click="goToViewMorePage(hostel.id)" id="hostel-click">
        <Hostel :hostel="hostel" />
      </div>
    </div>
  </div>
</template>

<script>
import HostelRequest from "../httpRequests/HostelRequest";
import Hostel from "../components/hostels/Hostel.vue";
import SearchBar from "../components/util/SearchBar";

export default {
  name: "Hostels",
  components: {
    Hostel,
    SearchBar,
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

    handleSearch(keyword) {
      HostelRequest.findHostels(keyword)
        .then((response) => {
          console.log("search result" + response.data);
          this.hostelList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },

  mounted() {
    this.getHostelList();
  },

  emits: ["handle-search"],
};
</script>

<style scope>
.hostels {
  padding: 2rem 4rem;
}

.hostel-list {
  margin: auto;
}

#hostel-click:hover {
  cursor: pointer;
}

.search-bar {
  float: right;
}
</style>
