const app = Vue.createApp({
    data(){
        return{
            description: "",
            lengthText: 0
        }
    },
    created(){
        
    },
    methods:{
        
    },
    computed:{
        counter(){
            return this.lengthText + this.description.length
        }
    }
})
app.mount("#app")