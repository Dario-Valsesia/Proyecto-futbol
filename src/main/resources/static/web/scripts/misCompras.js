const app = Vue.createApp({
    data(){
        return{
            datosCliente:[],
            data:{
                nombre: "",
                email: ""
            },
            //Productos comprados
            productos:[],
        }
    },
    created(){
        this.cargarDatosCliente();
    },
    methods:{
        cargarDatosCliente(){
            axios.get("/api/cliente/actual")
            .then(response => {
                this.datosCliente = response.data
                this.productos = this.datosCliente.productoClientes;
            }).catch(e=>e);
        },
        subscripcion(){
            swal({
                title: "¡Atención!",
                text: "¿Estás seguro/a que quieres subscribirte a nuestro newsletter?",
                icon: "warning",
                buttons: [true, "Continuar"]
            })
            .then(confrimation => {
                axios.post("/api/mail",`destino=${this.data.email}&nombreUsuario=${this.data.nombre}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                .then(response => {
                    swal({
                        title: "¡Genial!",
                        text: "Gracias por subscribirte a nuestro newsletter.",
                        icon: "success"
                    })
                    .then(confirmation => {
                        window.location.reload()
                    })
                })
            })
        },
        signOut(){
            swal({
                text: "¿Estás seguro/a que quieres cerrar su sesión?",
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
        },
    },
    computed:{

    },
})

let mount = app.mount("#app");