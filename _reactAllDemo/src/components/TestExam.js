/**
 * Created by tha on 12-10-2017.
 * Based on: "https://medium.com/@pshrmn/a-simple-react-router-v4-tutorial-7f23ff27adf"
 */
import React, { Component } from 'react';
import { Switch, Route, Link } from 'react-router-dom'
//import { Link } from 'react-router-dom';
import '../css/routing.css';
const url = "https://gist.githubusercontent.com/Thomas-Hartmann/1bab5f3b3ac8bbb6b4607db725e8ea20/raw/85fd536b2a9e2e25cac9ebcf9d2576686bfc7818/github_repos.json";

class RepoFacade{
    constructor(){
        this.repos = [];
    }
    getDataAsync = async ()=>{
        const data = await this.facadeA.loadData();
        this.setState({data});
    }
    loadData = async (cb)=>{
        return await fetch(url, {method:'GET'}).then((data)=>{
            return data.json();
        }).then((data)=>{
            this.repos = data;
        });
    }
    getRepo = (id)=>{
        const filtered = this.repos.filter((repo)=>repo.id == id);
        return filtered[0];
    }
    getAll = ()=>{
        return this.repos;
    }
}
export default class TestExam extends Component{
    constructor(){
        super();
        this.state = {repos: []};
        this.facade = new RepoFacade();
    }
    componentDidMount=()=>{
        const r = this.facade.getAll();
        this.setState({repos: r});
    }
    render(){
        const repos = this.state.repos.map((el)=><div><Link to={`/repo/${el.id}`}>{el.name}</Link></div>);
        return (<div>

            {repos}
            <br/>
            <h2>The repo you clicked was: </h2><br/>
                <Switch>
                    <Route path='/repo/:no' component={Repo}/>
                </Switch>
        </div>);
    }

}
const Repo = (props)=><div>{props.match.params.no}</div>