import React, {useEffect, useState} from 'react';
import {colors} from "@/ui/layout/styles/Globals";
import styled from "styled-components";
import SecondaryButton from "@/ui/components/buttons/SecondaryButton";

function SearchBar({users, onClick}) {
    const [searchText, setSearchText] = useState('');
    const [filteredUsers, setFilteredUsers] = useState(users);

    useEffect(() => {
        setFilteredUsers(
            users.filter((user) =>
                user.name.toLowerCase().includes(searchText.toLowerCase())
            )
        );
    }, [searchText, users]);

    const handleSearchTextChange = (event) => {
        setSearchText(event.target.value);
    };

    return (
        <div>
            <StyledSearchBar
                type="text"
                placeholder="Search Twitter"
                value={searchText}
                onChange={handleSearchTextChange}
            />
            <UserList
                style={
                    searchText.length > 0
                        ? {display: 'block'}
                        : {display: 'none'}
                }
            >
                {filteredUsers.map((user) => (
                    <li key={user.id}>
                        <SecondaryButton
                            value={`Follow ${user.name}`}
                            onClick={() => onClick(user.name)}
                        />
                    </li>
                ))}
            </UserList>
        </div>
    );
}

const StyledSearchBar = styled.input`
  width: 100%;
  height: 40px;
  border-radius: 20px;
  border: 1px solid ${colors.gray};
  padding: 0 20px;
  box-sizing: border-box;
  margin-bottom: 20px;
  font-size: 1.2rem;
  background-color: ${colors.black};
  color: ${colors.white};
`;

const UserList = styled.ul`
  list-style: none;
  padding: 0;
  margin: 0;
  background-color: ${colors.black};
  color: ${colors.white};

  li {
    padding: 5px;
    margin: 10px;
  }
`;


export default SearchBar;