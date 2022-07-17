#version 430 core

struct Agent {
    vec2 pos;
    vec2 dir;
};

layout (std430, binding = 0) buffer agentsBuffer
{
    Agent agents[];
};

layout (local_size_x = 360, local_size_y = 1, local_size_z = 1) in;

void main() {
    uint i = gl_GlobalInvocationID.x;
    agents[i].pos += agents[i].dir;
}