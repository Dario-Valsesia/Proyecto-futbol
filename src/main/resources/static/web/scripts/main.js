const app = Vue.createApp({
    data(){
        return{
            datosCliente: [],
            listaProductos: [],
            listaReservas: [],
            listaPorFecha: [],
            description: "",
            lengthText: 0
        }
    },
    created(){
        this.datosClienteActual()
        this.productos()
    },
    methods:{
        datosClienteActual(){
            axios.get("/api/cliente/actual")
            .then(response => {
                this.datosCliente = response.data
                this.listaReservas = response.data.reservas

                this.ordenarReservas(this.listaReservas)
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
        },
        productos(){
            axios.get("/api/productos")
            .then(response => {
                this.listaProductos = response.data   
            })
        },
        ordenarReservas(array){
            return array.sort((a, b) => {
                if(a.horaIngreso < b.horaIngreso){
                    return -1
                }else if(a.horaIngreso > b.horaIngreso){
                    return 1
                }else{
                    return 0
                }
            })
        },
        momentFecha(date){
            return moment(date).format("DD/MM/YYYY")
        },
        momentHora(date){
            return moment(date).format("HH:mm")
        },
        numberFormat(data){
            return numeral(data).format("$ 0,0.00")
        },
        cancelarReserva(id){
            const urlParams = new URLSearchParams(window.location.search);
            this.reservaID = id;

            swal({
                title: "Confirmación",
                text: "¿Estás seguro que quieres cancelar su reserva?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.delete(`/api/reservas/${this.reservaID}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                    .then(response => {
                        swal({
                            text: "Reserva cancelada.",
                            icon: "success",
                            button: "Volver"
                        })
                        .then(response =>{
                            window.location.replace("home.html")
                        })
                    })
                }
            })
        }
    },
    computed:{
        listaFechaActual(){
            let fechaActual = new Date()

            this.listaPorFecha = this.listaReservas.filter(reserva => {
                reserva.horaIngreso == fechaActual
            })
            return this.listaPorFecha
        },
        counter(){
            return this.lengthText + this.description.length
        }
    }
})
const consola = app.mount("#app")