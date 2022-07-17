#version 400 core

layout(location = 0) out vec4 outColor;
in vec4 vertexColor;

void main(void)
{
    outColor = vertexColor;
}