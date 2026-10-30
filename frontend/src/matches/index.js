import React, { useEffect, useState } from "react";
import { Table } from "reactstrap";

/* Component has to show all matches in a table with the following columns: Name, Start, Finish, White, Black, Winner. The data has to be fetched from the backend using the endpoint /api/v1/matches. The component has to use the useEffect and useState hooks.
*If the array of matches is empty, the component has to show a title h1 with the message "NO MATCHES", without the table.
*/
export default function MatchesListing() {
    const [matches, setMatches] = useState([]);

    useEffect(() => {
        fetch("/api/v1/matches", { method: "GET" })
            .then(response => response.json())
            .then((data) => setMatches(Array.isArray(data) ? data : []))
            .catch(() => setMatches([]));
    }, []);
    const userNameOf = (u) => u?.username ?? u?.name ?? "";

    if (!matches || matches.length === 0) {
        return <h1>NO MATCHES</h1>;
    }

    return (
        <Table role="table" className="table table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Start</th>
                    <th>Finish</th>
                    <th>White</th>
                    <th>Black</th>
                    <th>Winner</th>
                </tr>
            </thead>
            <tbody>
                {matches.map(match => (
                    <tr key={match.id}>
                        <td>{match.name}</td>
                        <td>{match.start ?? ""}</td>
                        <td>{match.finish ?? "-"}</td>
                        <td>{userNameOf(match.whitePlayer)}</td>
                        <td>{userNameOf(match.blackPlayer)}</td>
                        <td>{match.winner ? userNameOf(match.winner) : "-"}</td>
                    </tr>
                ))}
            </tbody>
        </Table>
    );

};