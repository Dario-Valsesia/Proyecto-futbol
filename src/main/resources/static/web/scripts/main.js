const app = Vue.createApp({
    data(){
        return{
            datosCliente: [],
            description: "",
            lengthText: 0
        }
    },
    created(){
        this.datosClienteActual()
    },
    methods:{
        datosClienteActual(){
            axios.get("/api/cliente/actual")
            .then(response => {
                this.datosCliente = response.data
            })
        },
        signOut(){
            swal({
                text: "¿Estás seguro que quieres cerrar su sesión?",
                icon:"warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.post("/api/logout")
                    .then(response=>{
                        window.location.replace("../index.html")
                    })
                }
            })
        }  
    },
    computed:{
        counter(){
            return this.lengthText + this.description.length
        }
    }
})
app.mount("#app")