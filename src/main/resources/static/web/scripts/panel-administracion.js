const app = Vue.createApp({
    data() {
        return {
            tablaProductos: []
        }
    },
    created(){
        axios.get("/api/productos")
        .then(response => {
            this.tablaProductos = response.data
            console.log(this.tablaProductos)
        })
    },
    methods:{
        
    },
    computed:{

    }
})
app.mount("#app")