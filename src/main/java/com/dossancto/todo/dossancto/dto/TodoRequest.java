package com.dossancto.todo.dossancto.dto;

import lombok.Data;

public record TodoRequest(String title, String description, boolean completed) {
}
