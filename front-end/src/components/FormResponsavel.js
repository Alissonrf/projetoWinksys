import react, { Component } from 'react'
import { Button } from 'antd';
import api from '../api'
import InputMask from "react-input-mask";
import valida_cpf from "../functions/ValidarCpf"

class FormResponsavel extends Component {

    state = {
        nome: "",
        cpf: ""
    }

    handleSubmit = (e) => {
        e.preventDefault()

        let { nome } = this.state
        let { cpf } = this.state

        api.post('/responsaveis', {
            nome,
            cpf,

        }, {
            headers: {
                'Content-Type': 'Application/json'
            }
        })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    handleInputChange = (e) => {

        this.setState({
            nome: e.target.value
        })

    }
    handleInputChangeCpf = (e) => {

        this.setState({
            cpf: e.target.value
        })

    }

    render() {

        const { nome } = this.state
        const { cpf } = this.state

        return (
            <section>
                <form onSubmit={this.handleSubmit}>
                    <div className="campo">
                        <label>nome:</label>
                        <input onChange={this.handleInputChange} type="text" placeholder="Nome"></input>
                    </div>

                    <div className="campo">
                        <label>cpf:</label>
                        <InputMask mask="999.999.999-99" onBlur={valida_cpf}>
                            {() => (
                                <input
                                    className="form-control"
                                    type="text"
                                    id="idCadCPF"
                                    name="cad_Cpf"
                                    required
                                    placeholder="222.444.888-99"
                                />
                            )}
                        </InputMask>
                    </div>
                    <Button id= "idButton" type="primary" htmlType="submit">
                        Submit
                    </Button>
                </form>
            </section>
        )
    }
}

export default FormResponsavel