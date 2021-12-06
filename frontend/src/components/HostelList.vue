<template>
    <div>
        <h1>Hostel List</h1>
        <div v-for="hostel in hostelList" :key="hostel.id">
            <h3>{{hostel.id}}. {{hostel.name}}</h3>
            <p>Rating: {{hostel.rating}}/5</p>
            <p>Location: {{hostel.location}}</p>
            <p>Description: {{hostel.description}}</p>
        </div>
    </div>
</template>

<script>
import HostelRequest from '../httpRequests/HostelRequest';
export default {
    name:"HostelList",
    data() {
        return {
            hostelList: [],
        }
    },
    methods: {
        getHostelList() {
            HostelRequest.getHostelList().then((response) => {
                console.log(response.data);
                this.hostelList = response.data;
            }).catch(error => {
                console.log(error);
            })
        }
    },
    mounted() {
        this.getHostelList();
    },
}
</script>

<style>

</style>