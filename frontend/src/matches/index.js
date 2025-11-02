import React, { useEffect, useState } from "react";
import { Table } from "reactstrap";

export default function MatchesLising() {

    const [matches, setMatches] = useState([]);

    useEffect(() => {
        // Fetch matches from API on mount
        fetch("/api/v1/matches", { method: "GET" })
            .then((r) => {
                if (!r.ok) throw new Error("Failed to fetch matches");
                return r.json();
            })
            .then((data) => setMatches(Array.isArray(data) ? data : []))
            .catch(() => setMatches([]));
    }, []);

    // Helper to display user's visible name
    const usernameOf = (u) => u?.name ?? u?.username ?? "";

    if (!matches || matches.length === 0) {
        return <h1>NO MATCHES</h1>;
    }
    // If you use reactstrap, set role="table" on Table as well.
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
            {/* tbody is optional for the tests, they don't rely on it */}
            <tbody>
                {matches.map((m) => (
                    <tr key={m.id}>
                        <td>{m.name}</td>
                        <td>{m.start ?? ""}</td>
                        <td>{m.finish ?? "-"}</td>
                        <td>{usernameOf(m.whitePlayer)}</td>
                        <td>{usernameOf(m.blackPlayer)}</td>
                        <td>{m.winner ? usernameOf(m.winner) : "-"}</td>
                    </tr>
                ))}
            </tbody>
        </Table>
    );
};