import { Button } from 'antd';
import react, { Component } from 'react'
import api from '../api'

class FormAtividadeDiaria extends Component {

    state = {
        dataPrevista: "",
        dataConclusao: "",
        descricao: "",
        // responsaveis: "",
        prioridade: "",
    }

    handleSubmit = (e) => {
        e.preventDefault()

        let { dataPrevista } = this.state
        let { dataConclusao } = this.state
        let { descricao } = this.state
        // let {responsaveis} = this.state
        let { prioridade } = this.state

        console.log('funcionou', dataPrevista)
        console.log('funcionou ', dataConclusao)
        console.log('funcionou ', descricao)
        // console.log('funcionou ', responsaveis)
        console.log('funcionou ', prioridade)

        // let dataConclusaoDate = new Date(dataConclusao);
        // let dataPrevistaDate = new Date(dataPrevista);

        api.post('/atividades', {
            descricao,
            dataConclusao,
            dataPrevista,
            // responsaveis,
            prioridade

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
            dataPrevista: e.target.value
        })

    }
    handleInputChangeConclusao = (e) => {

        this.setState({
            dataConclusao: e.target.value
        })

    }
    handleInputChangeDescricao = (e) => {

        this.setState({
            descricao: e.target.value
        })

    }
    handleInputChangeResponsaveis = (e) => {

        this.setState({
            responsaveis: e.target.value
        })

    }
    handleInputChangePrioridade = (e) => {

        this.setState({
            prioridade: e.target.value
        })

    }
    render() {

        const { dataPrevista } = this.state
        const { dataConclusao } = this.state
        const { descricao } = this.state
        const { responsaveis } = this.state
        const { prioridade } = this.state

        const onFinish = (values) => {
            console.log('Success:', values);
        };

        const onFinishFailed = (errorInfo) => {
            console.log('Failed:', errorInfo);
        };
        return (
            <section>
                <form onSubmit={this.handleSubmit}>
                    <div className="campo">
                        <label>Data Prevista:</label>
                        <input onChange={this.handleInputChange} type="Date" placeholder="DataPrevista"></input>
                    </div>
                    <div className="campo">
                        <label>Data Conclusao:</label>
                        <input onChange={this.handleInputChangeConclusao} type="Date" placeholder="DataConclusao"></input>
                    </div>
                    <div className="campo">
                        <label>Descricao:</label>
                        <input onChange={this.handleInputChangeDescricao} type="text" placeholder="Descricao"></input>
                    </div>
                    <div className="campo">
                        <label>responsaveis: </label>
                        <input onChange={this.handleInputChangeResponsaveis} type="text" placeholder="Responsaveis"></input>
                    </div>
                    <div className="campo">
                        <label>prioridade:  </label>
                        <input onChange={this.handleInputChangePrioridade} name="rate" type="text" placeholder="Prioridades"></input>
                    </div>
                    <Button type="primary" htmlType="submit">
                        Enviar
                    </Button>
                </form>
            </section>
        )
    }

}

export default FormAtividadeDiaria